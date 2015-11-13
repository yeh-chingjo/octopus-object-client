package com.ani.octopus.service.agent.service.account.dto;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-11-11.
 */
public class AccountModifyDto implements Serializable {
    private static final long serialVersionUID = -1481274272720507816L;

    public Long accountId;
    public String screenName;
    //public String email;
    //public String password;
    public AccountType accountType;

    public String phoneNumber;
    public String address;
    public String company;
    public String photoPath;

    public AccountModifyDto() {
    }

    public AccountModifyDto(Long accountId, String screenName, AccountType accountType,
                            String phoneNumber, String address, String company, String photoPath) {
        this.accountId = accountId;
        this.screenName = screenName;
        this.accountType = accountType;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.company = company;
        this.photoPath = photoPath;
    }

    public static AccountDto toAccountDto(AccountModifyDto modifyDto) {
        if (modifyDto == null) {
            return null;
        }
        AccountInfoDto accountInfoDto = new AccountInfoDto(
                modifyDto.phoneNumber,
                modifyDto.address,
                modifyDto.company,
                modifyDto.photoPath
        );
        return new AccountDto(
                modifyDto.accountId,
                modifyDto.screenName,
                null,
                null,
                modifyDto.accountType,
                accountInfoDto
        );
    }
}
