package com.example.backend.api.craftshop.application;

import com.example.backend.api.craftshop.domain.CraftShop;
import com.example.backend.api.craftshop.domain.CraftShopRepository;
import com.example.backend.api.craftshop.dto.RegisterCraftShopRequest;
import com.example.backend.api.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CraftShopService {
    private final CraftShopRepository craftShopRepository;

    public CraftShopService(CraftShopRepository craftShopRepository) {
        this.craftShopRepository = craftShopRepository;
    }

    public void registerCraftShop(Member member, RegisterCraftShopRequest registerCraftShopRequest) {
        CraftShop craftShop = registerCraftShopRequest.toEntity(member);
        craftShopRepository.save(craftShop);
    }
}
