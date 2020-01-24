package greencity.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration that is used for managing RabbitMQ-related settings.
 * It is responsible for exchanges and queues declarations as well as
 * binding them together.
 */
@Configuration
@EnableRabbit
public class EmailServiceRabbitConfig {
    @Value("${messaging.rabbit.email.topic}")
    private String emailTopicExchangeName;

    /**
     * Topic exchange declaration that is used for email-related queues.
     *
     * @return topic exchange for email-related messages and queues.
     */
    @Bean
    public TopicExchange emailTopicExchange() {
        return new TopicExchange(emailTopicExchangeName);
    }

    /**
     * Queue that is used for sending password recovery emails.
     * It is durable since password recovery is security related functionality.
     *
     * @return durable queue that is meant for sending password recovery email letters.
     */
    @Bean
    public Queue passwordRecoveryEmailQueue() {
        return new Queue("password-recovery-queue", true);
    }

    /**
     * The binding that is used for linking email topic exchange to password recovery email queue.
     *
     * @return Binding with topic exchange and password recovery queue linked.
     */
    @Bean
    public Binding passwordRecoveryQueueToEmailTopicBinding(TopicExchange emailTopicExchange) {
        return BindingBuilder
            .bind(passwordRecoveryEmailQueue())
            .to(emailTopicExchange)
            .with("password.recovery");
    }
}