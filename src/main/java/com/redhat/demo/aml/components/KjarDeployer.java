package com.redhat.demo.aml.components;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

@Component
public class KjarDeployer {
    private static final KieServices KIE_SERVICES = KieServices.Factory.get();

    public static KieContainer kieContainer;

    public KjarDeployer() {
        ReleaseId rId1 = KIE_SERVICES.newReleaseId("com.redhat.demo", "aml-rules", "1.0.0-SNAPSHOT");
        kieContainer = KIE_SERVICES.newKieContainer(rId1);

        // Initialize to get rid of first delay
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.dispose();
    }
}
