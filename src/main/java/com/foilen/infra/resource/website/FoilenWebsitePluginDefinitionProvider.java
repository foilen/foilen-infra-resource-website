/*
    Foilen Infra Resource Website
    https://github.com/foilen/foilen-infra-resource-website
    Copyright (c) 2018 Foilen (http://foilen.com)

    The MIT License
    http://opensource.org/licenses/MIT

 */
package com.foilen.infra.resource.website;

import java.util.Arrays;

import com.foilen.infra.plugin.v1.core.context.CommonServicesContext;
import com.foilen.infra.plugin.v1.core.plugin.IPPluginDefinitionProvider;
import com.foilen.infra.plugin.v1.core.plugin.IPPluginDefinitionV1;

public class FoilenWebsitePluginDefinitionProvider implements IPPluginDefinitionProvider {

    @Override
    public IPPluginDefinitionV1 getIPPluginDefinition() {
        IPPluginDefinitionV1 pluginDefinitionV1 = new IPPluginDefinitionV1("Foilen", "Website", "To manage websites", "1.0.0");

        pluginDefinitionV1.addCustomResource(Website.class, Website.RESOURCE_TYPE, //
                Arrays.asList( //
                        Website.PROPERTY_NAME //
                ), //
                Arrays.asList( //
                        Website.PROPERTY_NAME, //
                        Website.PROPERTY_DOMAIN_NAMES //
                ));

        pluginDefinitionV1.addTranslations("/com/foilen/infra/resource/website/messages");
        pluginDefinitionV1.addResourceEditor(new WebsiteEditor(), WebsiteEditor.EDITOR_NAME);

        pluginDefinitionV1.addUpdateHandler(new MachineHaProxyUpdateHandler());
        pluginDefinitionV1.addUpdateHandler(new WebsiteUpdateHandler());

        return pluginDefinitionV1;
    }

    @Override
    public void initialize(CommonServicesContext commonServicesContext) {
    }

}
