package com.example.backend.api.craftshop.presentation;

import com.example.backend.api.craftshop.application.CraftShopService;
import com.example.backend.api.craftshop.dto.RegisterCraftShopRequest;
import com.example.backend.api.member.domain.Member;
import com.example.backend.common.security.annotations.Authenticated;
import com.example.backend.common.security.annotations.MemberClaim;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/craftShop")
public class CraftShopController {
    private final CraftShopService craftShopService;

    public CraftShopController(CraftShopService craftShopService) {
        this.craftShopService = craftShopService;
    }

    @Authenticated
    @PostMapping("register")
    public ResponseEntity<?> registerCraftShop(@ApiIgnore @MemberClaim Member member, @RequestBody RegisterCraftShopRequest registerCraftShopRequest) {
        craftShopService.registerCraftShop(member, registerCraftShopRequest);
        return ResponseEntity.ok()
                .build();
    }
}
