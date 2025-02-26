package org.acme.endpoint;

import java.math.BigInteger;
import org.acme.calculo.LabSeqCalculo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Path("/labseq")
public class LabSeqResource {

    @Inject
    LabSeqCalculo labSeqCalculo;

    public static class ErrorResponse {
        public String error;
        public ErrorResponse() {}
        public ErrorResponse(String error) { this.error = error; }
    }

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Get LabSeq value",
        description = "Calculate the LabSeq sequence value for a non-negative integer n."
    )
    @Parameter(
        name = "n",
        description = "Non-negative integer index",
        required = true,
        schema = @Schema(
            minimum = "0",
            type = SchemaType.INTEGER, 
            format = "int32"
        )
    )
    @APIResponse(
        responseCode = "200",
        description = "Successfully calculated l(n)",
        content = @Content(
            schema = @Schema(
                type = SchemaType.INTEGER,
                format = "int64"
            )
        ) 
    )
    @APIResponse(
        responseCode = "400",
        description = "Invalid input",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    public Response getLabSeqValue(@PathParam("n") int n) {
        try {
            BigInteger result = labSeqCalculo.calculate(n);
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                       .entity(new ErrorResponse(e.getMessage()))
                       .build();
        }
    }
}