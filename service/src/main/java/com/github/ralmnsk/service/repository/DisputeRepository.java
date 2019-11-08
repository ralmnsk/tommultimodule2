package com.github.ralmnsk.service.repository;

import com.github.ralmnsk.service.dispute.Dispute;
import org.springframework.data.repository.CrudRepository;

public interface DisputeRepository extends CrudRepository<Dispute, Long> {
}
