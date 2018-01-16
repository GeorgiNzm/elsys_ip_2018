package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Weapon;
import org.elsys.ip.rest.repository.WeaponRepository;

import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.util.List;

public class WeaponService {
    private WeaponRepository weaponRepository = new WeaponRepository();

    public List<Weapon> getWeaponList(MultivaluedMap<String, String> queryParameters) {
        return weaponRepository.getFilteredWeaponList(queryParameters);
    }

    public Weapon getWeaponById(Integer id) {
        return weaponRepository.getWeaponById(id).orElse(null);
    }

    public Weapon saveWeapon(Weapon w) {
        return weaponRepository.saveWeapon(w);
    }

    public Weapon updateWeapon(Integer id, Weapon w) {
        return weaponRepository.updateWeapon(id, w);
    }

    public void deleteWeapon(Integer id) {
        weaponRepository.deleteWeapon(id);
    }

    public File downloadCSV() { return weaponRepository.downloadCSV(); }
}
