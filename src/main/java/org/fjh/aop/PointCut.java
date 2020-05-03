package org.fjh.aop;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PointCut {
    private  String matchPackages;
    private  String matchMethods;
}
