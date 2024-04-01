<template>
    <div class="margen">
        <div class="bg-img">
            <form action="#" class="container" method="POST" @submit.prevent="registrarNota">
                <div class="tituloradio">
                    <h1 class="titulo">REGISTRAR NOTAS</h1>
                    <div class="img">
                        <img src="../../public/img/registrarse.png" height="80px">
                    </div><br><br>
                    <label for="id"><b></b></label>
                    <input class="id" type="text" placeholder="ID" name="id" v-model="id" required><br><br>

                    <label for="titulo"><b></b></label>
                    <input class="campos" type="text" placeholder="Titulo" name="titulo" v-model="titulo" required><br><br>

                    <label for="fecha"><b></b></label>
                    <input class="campos" type="text" placeholder="Fecha" name="fecha" v-model="fecha" required
                        ><br><br>

                    <label for="contenido"><b></b></label>
                    <input class="campos" type="text" placeholder="Contenido" name="contenido" v-model="contenido" required><br><br>



                    <div class="botones">
                        <button type="submit" class="boton">Registrar Notas</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        name: "CRegistroNotas"
        return {
            id: "",
            titulo: "",
            fecha: "",
            contenido: "",
            notas: ""
        }
    },
    components: {

    },
    methods: {
        registrarNota() {
            if (this.id != "" && this.titulo != "" && this.fecha != "" && this.contenido != "") {
                var endpoint = "http://localhost:8080/Agendas/guardar";
                var opciones = {
                    method: "POST",
                    headers: { "Content-type": "Application/json" },
                    body: JSON.stringify({
                        id: this.id,
                        titulo: this.titulo,
                        fecha: this.fecha,
                        contenido: this.contenido,

                    }),
                };
                fetch(endpoint, opciones).then(async (Response) => {
                    try {
                        if (Response.status)
                            Swal.fire({
                                icon: "question",
                                title: "Desea Registrar nota?",
                                showDenyButton: true,
                                showCancelButton: false,
                                confirmButtonText: "Registrar",
                                denyButtonText: `Cancelar`,
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    Swal.fire("Guardado con exito", "", "success");
                                    this.id =null,
                                    this.titulo=" ",
                                    this.fecha=null,
                                    this.contenido=" " 
                                    this.$router.push("/login")       
                                } else if (result.isDenied) {
                                    Swal.fire("Los cambios no se guardaron", "", "warning");
                                    return false;
                                }
                            });
                        else {
                            Swal.fire("Algo salio mal -_-", "warning");
                            return false;
                        }
                    } catch (error) {
                        return '{"sucess":false ,"message":"Error, Intentalo nuevamente."}';
                    }
                })
            }
        },

        cargarNotas: async function (event) {
            try {

                url = "http://localhost:8080/notas/"
                
                this.notas = await fetch(url, {
                    method: "GET"

                }).then((response) => response.json())
                    .then(data => data)
                    .catch(error => console.log(error))

                
                this.notas = this.notas.data
                console.log(this.notas)

            } catch (error) {
                swal({
                    title: "Error!",
                    text: "Ocurrió un error al intertar comunicarnos con el servidor :(",
                    icon: "error"
                })

            }
        },

        eliminarNota: function (event) {
            try {
                resultado = confirm("Quieres eliminar el nota?")

                if (resultado) {
                    id = event.target.id

                    url = "http://localhost:8080/notas/eliminar/" + id

                    respuesta = fetch(url, {
                        method: "DELETE"

                    }).then((response) => response.json())
                        .then(data => data)
                        .catch(error => console.log(error))

                    swal({
                        title: "Éxito!",
                        text: "Se elimino correctamente el nota.",
                        icon: "success"
                    })

                    
                }


            } catch (error) {

            }
        },


        beforeMount(){
            this.cargarNotas()
        }
    }
}

</script>