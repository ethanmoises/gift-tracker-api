package com.geranium.gift.service.impl;

import com.geranium.gift.exception.ResourceNotFoundException;
import com.geranium.gift.mapper.GiftMapper;
import com.geranium.gift.model.dto.GiftRequestDTO;
import com.geranium.gift.model.dto.GiftResponseDTO;
import com.geranium.gift.model.entity.Gift;
import com.geranium.gift.model.entity.Person;
import com.geranium.gift.model.enums.GiftStatus;
import com.geranium.gift.repository.GiftRepository;
import com.geranium.gift.repository.PersonRepository;
import com.geranium.gift.repository.specification.GiftSpecification;
import com.geranium.gift.service.GiftService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;
    private final PersonRepository personRepository;
    private final GiftMapper giftMapper;

    public GiftServiceImpl(GiftRepository giftRepository,
                           PersonRepository personRepository,
                           GiftMapper giftMapper) {
        this.giftRepository = giftRepository;
        this.personRepository = personRepository;
        this.giftMapper = giftMapper;
    }

    @Override
    public GiftResponseDTO addGift(Long personId,
                                   GiftRequestDTO request) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Person not found with id: " + personId));

        Gift gift = giftMapper.toEntity(request);

        person.addGift(gift);

        personRepository.save(person);

        return giftMapper.toResponseDTO(gift);
    }

    @Override
    @Transactional(readOnly = true)
    public GiftResponseDTO getGiftById(Long id) {

        Gift gift = giftRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Gift not found with id: " + id));

        return giftMapper.toResponseDTO(gift);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GiftResponseDTO> getAllGifts(String idea,
                                             GiftStatus status,
                                             Double minPrice,
                                             Double maxPrice,
                                             String personName) {

        Specification<Gift> spec = Specification
                .where(GiftSpecification.ideaContains(idea))
                .and(GiftSpecification.hasStatus(status))
                .and(GiftSpecification.priceBetween(minPrice, maxPrice))
                .and(GiftSpecification.belongsToPerson(personName));

        return giftRepository.findAll(spec, Sort.by("price"))
                .stream()
                .map(giftMapper::toResponseDTO)
                .toList();
    }

    @Override
    public GiftResponseDTO updateGift(Long id,
                                      GiftRequestDTO request) {

        Gift gift = giftRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Gift not found with id: " + id));

        gift.setIdea(request.idea());
        gift.setPrice(request.price());
        gift.setLink(request.link());
        gift.setStatus(request.status());


        return giftMapper.toResponseDTO(gift);
    }

    @Override
    public void deleteGift(Long id) {

        Gift gift = giftRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Gift not found with id: " + id));

        Person person = gift.getPerson();

        person.removeGift(gift);

        personRepository.save(person);
    }
}