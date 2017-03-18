package com.example.devtoolindex.contributor;

import com.example.devtoolindex.helper.Helper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * Created by hongkailiu on 2017-03-17.
 */
@Component
public class MyInfoContributor implements InfoContributor {

  @Value("${deployed.by}")
  private String deployedBy;

  @Value("${secret.key1}")
  private String secretKey1;

  @Autowired
  private Helper helper;

  @Override
  public void contribute(Info.Builder builder) {
    Map<Object, Object> map = new HashMap();
    map.put("version", helper.getAppVersion());
    map.put("deployedBy", deployedBy);
    map.put("secretKey1", secretKey1);
    builder.withDetail("AppInfo", map);
  }
}
