package com.yesipov.api;

import com.yesipov.dao.ImageDao;
import com.yesipov.model.Image;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.util.Base64;

@Path("/")
public class ImageApi {
    ImageDao imageDao = new ImageDao();

    @POST
    @Path("/addImage")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addImage(@FormParam("imageBase64") String imageBase64,
                             @FormParam("descript") String descript) {
        Image image = new Image(imageBase64, descript);
        return Response
                .status(Response.Status.OK)
                .entity("Image posted ID = " + imageDao.addImage(image))
                .build();
    }

    @Path("/getImages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllImages() {
        return Response
                .status(Response.Status.OK)
                .entity(imageDao.getAllImages().toString())
                .build();
    }


    @Path("/{id}")
    @GET
    @Produces("image/png")
    public Response getImageById(@PathParam("id") long id) {
        try {
            byte[] arr = Base64.getDecoder()
                    .decode(imageDao.getImageById(id).getImageBase64());
            return Response
                    .ok(new ByteArrayInputStream(arr))
                    .build();
        } catch (NoResultException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage() + "\n NO IMAGE WITH SUCH ID")
                    .build();
        }
    }
}
