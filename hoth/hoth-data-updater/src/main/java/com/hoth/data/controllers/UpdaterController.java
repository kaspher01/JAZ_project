package com.hoth.data.controllers;

import com.hoth.data.repositories.ICatalogData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UpdaterController {

    private final ICatalogData db;
}
