package com.fresher.fresherserivce.config.birtconfig;

// package com.lthdv.authservice.config.birtconfig;
//
// import org.eclipse.birt.core.exception.BirtException;
// import org.eclipse.birt.core.framework.Platform;
// import org.eclipse.birt.report.engine.api.EngineConfig;
// import org.eclipse.birt.report.engine.api.IReportEngine;
// import org.eclipse.birt.report.engine.api.IReportEngineFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// public class BirtConfig {
//
// @Bean
// public IReportEngine reportEngine() throws BirtException {
// EngineConfig config = new EngineConfig();
// Platform.startup(config);
//
// IReportEngineFactory factory = (IReportEngineFactory) Platform
// .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
// return factory.createReportEngine(config);
// }
// }