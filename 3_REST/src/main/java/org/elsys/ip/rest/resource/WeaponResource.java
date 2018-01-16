package org.elsys.ip.rest.resource;

import com.opencsv.CSVReader;
import org.elsys.ip.rest.model.Weapon;
import org.elsys.ip.rest.repository.WeaponRepository;
import org.elsys.ip.rest.service.WeaponService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Path("weapons")
public class WeaponResource {
    private WeaponService weaponService = new WeaponService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Weapon> getWeaponList(@Context UriInfo info) {
        return weaponService.getWeaponList(info.getQueryParameters());
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Weapon getWeaponById(@PathParam("id") Integer id) {
        return weaponService.getWeaponById(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Weapon saveWeapon(Weapon w) {
        return weaponService.saveWeapon(w);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Weapon updateWeapon(@PathParam("id") Integer id, Weapon w) {
        return weaponService.updateWeapon(id, w);
    }

    @DELETE
    @Path("/{id}")
    public void deleteWeapon(@PathParam("id") Integer id) {
        weaponService.deleteWeapon(id);
    }

    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadCSVFile() {
        File file = weaponService.downloadCSV();

        return Response
                .ok(file)
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .build();
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Weapon> uploadCSVFile(InputStream fileInput) {

        List<Weapon> latestWeapons = new LinkedList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput))) {
            String fileLine = "";

            while ((fileLine = reader.readLine()) != null) {

                if (!fileLine.matches("[0-9].*")) continue;

                String[] properties = fileLine.split(",");

                if (Integer.parseInt(properties[0]) <= WeaponRepository.getWeaponList().size() - 1) break;

                Weapon latest = new Weapon(
                        Integer.parseInt(properties[0]),
                        properties[1],
                        properties[2],
                        properties[3],
                        properties[4],
                        properties[5],
                        Double.parseDouble(properties[6]),
                        properties[7],
                        properties[8],
                        properties[9],
                        Double.parseDouble(properties[10])
                );

                if (!WeaponRepository.getWeaponList().contains(latest)) latestWeapons.add(latest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Adding new weapons to existing list
        WeaponRepository.getWeaponList().addAll(latestWeapons);

        return latestWeapons;
    }

    @POST
    @Path("/multiple")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Weapon> saveMultipleWeapons(List<Weapon> latest) {
        latest.stream().forEach(weapon -> {
            if (!WeaponRepository.getWeaponList().contains(weapon)) weaponService.saveWeapon(weapon);
        });

        List<Weapon> result = new LinkedList<>();
        result.addAll(WeaponRepository.getWeaponList());
        return result;
    }
}
