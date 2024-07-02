package fpl.but.datn.service.impl;

import fpl.but.datn.entity.MauSac;
import fpl.but.datn.repository.MauSacRepository;
import fpl.but.datn.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MauSacService implements IMauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

}
