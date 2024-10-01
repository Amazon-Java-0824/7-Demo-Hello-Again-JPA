package com.scorsaro.helloagainjpa.demo;

import com.scorsaro.helloagainjpa.model.VolcanoEntity;
import com.scorsaro.helloagainjpa.service.VolcanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VolcanoService volcanoService;
    @Override
    public void run(String... args) throws Exception {

        VolcanoEntity volcano1 = new VolcanoEntity("Etna", 3369, true);
        VolcanoEntity volcano2 = new VolcanoEntity("Teide", 3715, true);

        System.out.println("Saving volcanoes");
        volcanoService.save(volcano1);
        volcanoService.save(volcano2);

        System.out.println("Finding volcanoes");
        System.out.println(volcanoService.findAll());
        System.out.println("Finding volcano 2");
        System.out.println(volcanoService.findById(2L));

        System.out.println("Deleting volcano 2");
        volcanoService.deleteById(2L);
        System.out.println(volcanoService.findById(2L));

        System.out.println("Updating Etna");
        System.out.println(volcanoService.update(1L, new VolcanoEntity("Mount Etna", 3369, true)));


        System.out.println("Retrieving all volcanoes that are active");
        var volcano3 = new VolcanoEntity("Vesuvius", 1281, true);
        var volcano4 = new VolcanoEntity("Krakatoa", 813, false);
        volcanoService.save(volcano3);
        volcanoService.save(volcano4);
        System.out.println(volcanoService.findAllTheVolcanoesThatAreActive());
        System.out.println(volcanoService.findAllTheVolcanoesThatAreNotActive());

        System.out.println("minimum height 1000");
        System.out.println(volcanoService.findAllVolcanoesWithMinimumHeight(1000));

        System.out.println("between");
        System.out.println(volcanoService.findAllActiveVolcanoesWithHeightBetween(900, 3000));

        System.out.println("name filter");
        System.out.println(volcanoService.findAllByNameContainingIgnoreCaseOrderByNameDesc("A"));

        System.out.println("JPQL");
        System.out.println(volcanoService.findByNameJPQL("Mount Etna"));
        System.out.println("Native");
        System.out.println(volcanoService.findByNameNative("Mount Etna"));

        System.out.println("Find by id only if active");
        System.out.println(volcanoService.findFirstByIdAndIsActiveTrue(1L));
        System.out.println(volcanoService.findFirstByIdAndIsActiveTrue(4L));


    }
}
