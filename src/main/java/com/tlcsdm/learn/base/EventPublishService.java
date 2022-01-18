package com.tlcsdm.learn.base;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 事件发布
 *
 * @author: TangLiang
 * @date: 2021/4/17 20:02
 * @since: 1.0
 */
@Service
public class EventPublishService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
