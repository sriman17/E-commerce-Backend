package com.restapi.sb.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.sb.model.CloudVendor;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, String> {
    List<CloudVendor> findByVendorName(String vendorName);
}