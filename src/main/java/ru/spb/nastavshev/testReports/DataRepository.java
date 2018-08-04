package ru.spb.nastavshev.testReports;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DataRepository extends CrudRepository<UserData, Long> {

    List<UserData> findByType(String type);
    List<UserData> findByGrp(String grp);

}
