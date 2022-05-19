package com.example.backend.api.craftshop.application;

import com.example.backend.api.craftshop.domain.BusinessDay;
import com.example.backend.api.craftshop.domain.BusinessDayRepository;
import com.example.backend.api.craftshop.domain.CraftShop;
import com.example.backend.api.craftshop.domain.CraftShopRepository;
import com.example.backend.api.craftshop.dto.RegisterCraftShopRequest;
import com.example.backend.api.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CraftShopService {
    private final CraftShopRepository craftShopRepository;
    private final BusinessDayRepository businessDayRepository;

    public CraftShopService(CraftShopRepository craftShopRepository, BusinessDayRepository businessDayRepository) {
        this.craftShopRepository = craftShopRepository;
        this.businessDayRepository = businessDayRepository;
    }

    public void registerCraftShop(Member member, RegisterCraftShopRequest registerCraftShopRequest) {
        CraftShop craftShop = registerCraftShopRequest.toCraftShopEntity(member);
        craftShopRepository.save(craftShop);

        List<BusinessDay> businessDays = registerCraftShopRequest.toBusinessDaysEntity(craftShop);
        businessDayRepository.saveAll(businessDays);
    }
}
