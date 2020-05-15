package com.iscolt.micm.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * IP 地址信息
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/3/2020
 * @see: com.iscolt.micm.commons.dto
 * @version: v1.0.0
 */
@Data
public class IpInfo implements Serializable {

    private static final long serialVersionUID = 4735885424086179334L;

    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;
    private String country_id;
    private String area_id;
    private String region_id;
    private String city_id;
    private String county_id;
    private String isp_id;
}
