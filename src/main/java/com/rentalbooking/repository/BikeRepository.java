package com.rentalbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rentalbooking.model.Bike;
@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {
	@Query(value="select * from Bike where city=?3 and status='AVAILABLE' and date between ?1 and ?2 group by vendor_id,model", nativeQuery=true)
	List<Bike> getBikes(LocalDate from,LocalDate to,String city);
	
	@Query(value = "update Bike set status='BOOKED' where model=?3 and vendor_id=?4 and date>=from and date<=to",nativeQuery = true)
	int updateBikes(LocalDate from,LocalDate to,String model,int vendorid);

}
