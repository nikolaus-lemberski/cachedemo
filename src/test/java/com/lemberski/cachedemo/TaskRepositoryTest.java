package com.lemberski.cachedemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setDescription("Task description");
        task.setDone(false);

        task = taskRepository.save(task);

        assertThat(task.getId()).isNotNull();
        assertThat(task.getDescription()).isEqualTo("Task description");
        assertThat(task.getDone()).isFalse();
    }

}
