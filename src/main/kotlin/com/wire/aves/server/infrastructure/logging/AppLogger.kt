package com.wire.aves.server.infrastructure.logging

import org.slf4j.LoggerFactory

/**
 * todo(ym)
 * - Create loggers with detailed/structured form.
 * ie: FlowID, and other stuff, for log analysis
 * - Add MDC config for wide req info.
 */
object AppLoggers {
    val applicationLogger by lazy { LoggerFactory.getLogger("ApplicationLayerLogger") }
    val domainLogger by lazy { LoggerFactory.getLogger("DomainLayerLogger") }
    val infrastructureLogger by lazy { LoggerFactory.getLogger("InfrastructureLayerLogger") }
}