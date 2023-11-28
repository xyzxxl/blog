package cn.xyz.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
  private Long id;
  private String name;
  private String url;
  private String descs;
  private String sn;
}
