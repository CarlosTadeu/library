package com.opussoftware.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.opussoftware.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.opussoftware.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.opussoftware.domain.User.class.getName());
            createCache(cm, com.opussoftware.domain.Authority.class.getName());
            createCache(cm, com.opussoftware.domain.User.class.getName() + ".authorities");
            createCache(cm, com.opussoftware.domain.LibraryUser.class.getName());
            createCache(cm, com.opussoftware.domain.Author.class.getName());
            createCache(cm, com.opussoftware.domain.Author.class.getName() + ".books");
            createCache(cm, com.opussoftware.domain.Book.class.getName());
            createCache(cm, com.opussoftware.domain.Book.class.getName() + ".copyBooks");
            createCache(cm, com.opussoftware.domain.Book.class.getName() + ".subjects");
            createCache(cm, com.opussoftware.domain.Book.class.getName() + ".authors");
            createCache(cm, com.opussoftware.domain.CopyBook.class.getName());
            createCache(cm, com.opussoftware.domain.Loan.class.getName());
            createCache(cm, com.opussoftware.domain.StudentType.class.getName());
            createCache(cm, com.opussoftware.domain.Subject.class.getName());
            createCache(cm, com.opussoftware.domain.Subject.class.getName() + ".books");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
