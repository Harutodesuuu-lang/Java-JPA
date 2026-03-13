package co.istad.suiii.fswd.sbapp.ValueAnnotation;

import co.istad.suiii.fswd.sbapp.Bean.MyAnnotationBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

record AppInfo (String name, Integer appPort) {}

@RestController
public class ValueAnnotationController {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${server.port}")
    private Integer appPort;
    @GetMapping("/info")
    public AppInfo getAppinfo() {
        return new AppInfo(appName, appPort);

    }

}
