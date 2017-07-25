package com.nerney;

import com.nerney.domain.Offender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nerney on 7/14/2017.
 */
@Repository
public interface OffenderRepository extends CrudRepository<Offender, Long> {
}
