package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Weapon;

import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeaponRepository {
    private static List<Weapon> weaponList = new LinkedList<>(
            Arrays.asList(
              new Weapon(1, "Pistol", "Delta Elite",
                      "Colt", "GOVERNMENT",
                      "10mm",
                      5.0, "8+1", "Polished",
                      "Stainless Steel", 1.07),

                    new Weapon(2, "Pistol", "M45A1",
                            "Colt", "GOVERNMENT",
                            ".45 ACP",
                            5.0, "7+1", "Polished",
                            "Stainless Steel", 1.14),

                    new Weapon(3, "Rifle", "M4",
                            "Colt", "M4 CARBINE",
                            "5.56x45mm",
                            16.1, "30+1", "Manganese Phosphate",
                            "7075-T6 Aluminum", 2.92),

                    new Weapon(4, "Rifle", "M&P",
                            "SmithWesson", "M&P®15-22 SPORT™ MOE SL® Flat Dark Earth",
                            ".22 LR",
                            16.5, "25", "Polymer",
                            "Carbon Steel", 2.24),

                    new Weapon(5, "Rifle", "M.A.R.C.901",
                            "Colt", "MODULAR CARBINE",
                            ".308 WIN",
                            16.1, "20+1", "Manganese Phosphate",
                            "7075-T6 Aluminum", 4.05)

            )
    );

    public WeaponRepository() {}

    public static List<Weapon> getWeaponList() {
        return weaponList;
    }

    public Optional<Weapon> getWeaponById(Integer id) {
        return getWeaponList().stream()
                              .filter(weapon -> weapon.getId().equals(id))
                              .findFirst();
    }

    public Weapon saveWeapon(Weapon w) {
        getWeaponList().add(w);
        return w;
    }

    public Weapon updateWeapon(Integer id, Weapon w) {
        getWeaponList().set(id - 1, w);
        return w;
    }

    public void deleteWeapon(Integer id) {
        getWeaponList().removeIf(w -> w.getId().equals(id));
    }

    public List<Weapon> getFilteredWeaponList(MultivaluedMap<String, String> queryParameters) {
        List<Weapon> copiedList = new LinkedList<>(getWeaponList());

        Set<String> keys = queryParameters.keySet();

        if (queryParameters.isEmpty()) return getWeaponList();

        Stream<Weapon> resulting = null;

        for (String key : keys) {
            List<String> values = queryParameters.get(key);

            for (String value : values) {
                switch (key) {
                    case "id":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getType()
                                                .equals(Integer.parseInt(value)));
                        break;
                    case "type":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getType().equals(value));
                        break;

                    case "category":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getCategory().equals(value));
                        break;

                    case "brand":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getBrand().equals(value));
                        break;

                    case "model":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getModel().equals(value));
                        break;

                    case "caliber":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getCaliber().equals(value));
                        break;

                    case "barrelLength":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getBarrelLength()
                                                .equals(Double.parseDouble(value)));
                        break;

                    case "capacity":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getCapacity().equals(value));
                        break;

                    case "frameFinish":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getFrameFinish().equals(value));
                        break;

                    case "material":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getMaterial().equals(value));
                        break;

                    case "weight":
                        resulting = copiedList.stream()
                                .filter(weapon ->
                                        weapon.getWeight()
                                                .equals(Double.parseDouble(value)));
                        break;
                }
            }
        }

        return resulting.collect(Collectors.toList());
    }

    public List<Weapon> getFilteredByIds(MultivaluedMap<String, String> query, List<Weapon> copiedList) {
        List<String> values = query.get("id");

        List<Weapon> filtered = new LinkedList<>();

        for (String value : values) {

            filtered.add(copiedList.stream()
                    .filter(weapon -> weapon.getId() == Integer.parseInt(value))
                    .findFirst().orElse(null));
        }

        return filtered;
    }

    public File downloadCSV() {
        File file = new File("weaponsCSV.csv");

        try(FileWriter filew = new FileWriter(file)) {
            for (Weapon weapon : getWeaponList()) {
                filew.write(weapon.toCSV() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
