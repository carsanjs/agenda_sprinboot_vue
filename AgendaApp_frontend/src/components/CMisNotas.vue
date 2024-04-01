<template>
    <div class="margen">
        <div class="bg-img">
            <form action="#" class="container">
                <div class="tituloradio">
                    <h1 class="titulo">CONSULTAR NOTAS</h1>
                    <div>
                      
                    <div v-if="(i==0)"  >
                        <div class="img">
                        <img src="../../public/img/registrarse.png" height="80px">
                       </div>
                        	
                    </div>
                        
                    <div  v-show="(i>=1)">
                        <form action="#" class="container" method="POST" @submit.prevent="registrarNota" autocomplete="off">
                <div class="tituloradio">
                    <label for="id"><b></b></label>
                    <input class="id" type="text" placeholder="ID" name="id" v-model="id" required disabled><br><br>

                    <label for="titulo"><b></b></label>
                    <input class="campos" type="text" placeholder="Titulo" name="titulo" v-model="titulo" required><br><br>

                    <label for="fecha"><b></b></label>
                    <input class="campos" type="text" placeholder="Fecha" name="fecha" v-model="fecha" required
                        ><br><br>

                    <label for="contenido"><b></b></label>
                    <input class="campos" type="text" placeholder="Contenido" name="contenido" v-model="contenido" required><br><br>



                    <div class="botones">
                        <button class="boton btn-primary"  type="button" v-on:click="removeNotas">eliminar</button>
                        <button class="boton btn-success" type="button" v-on:click="ActualizarNotas">Actualizar</button>
                        <!-- <button type="submit" class="boton">Registrar Notas</button> -->
                    </div>
                </div>
            </form>
                    </div>

                       
                    </div>
                    <br><br>

                    <table class="table table-stipped">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Titulo</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Contenido</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item,index) in usuario">
                                 <th scope="row">{{item.id}}</th>
                                <th scope="row">{{item.titulo}}</th>
                                <th scope="row">{{item.fecha}}</th>
                                <th scope="row">{{item.contenido}}</th>
                                <td>
                                    <div class="row">
                                        <div class="col-6">
                                          
                                            <input type="button"  class="btn-primary"  @click="update(index), i+=1" value="Editar">
                                            <!-- <router-link to="/registronotas"> <button class="btn-success">Registrar</button></router-link>                                             -->
                                        </div>
                                    </div>
                                </td>

                            </tr>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </div>


</template>

<script>

export default{
    data() {
        return {
            id:'',
            titulo:"",
            fecha:"",
            contenido:"",
            usuario:{},
            i:0
            
        }
    },
    components:{

    },
    methods:{
        BuscarUsuario() {
            var endpoint = "http://localhost:8080/Agendas/verNotas";
            var opciones = { method: "GET" };
            fetch(endpoint, opciones).then( response => response.json())
            .then( data => {this.usuario = data; console.log(data)})    
        },

    update(index){
      this.id = this.usuario[index].id;
      this.titulo =  this.usuario[index].titulo;
      this.fecha = this.usuario[index].fecha;
      this.contenido = this.usuario[index].contenido;
      this.i = index; 
    },
    ActualizarNotas() {
            var endpoint = "http://localhost:8080/Agendas/guardar"
            var opciones = {
                method: "POST",
                headers: { "Content-type": "Application/json" },
                body: JSON.stringify({ 
                  id: this.id,
          titulo: this.titulo,
          fecha: this.fecha,
          contenido: this.contenido,
             })
            }
            fetch(endpoint, opciones).then(async Response => {
                // alert('Guardado Correctamente');
                try {
                    if (Response.status)
                        Swal.fire({
                            icon: 'question',
                            title: 'Desea Actualizar Tarea?',
                            showDenyButton: true,
                            showCancelButton: false,
                            confirmButtonText: 'Actualizar',
                            denyButtonText: `Cancelar`,
                        }).then((result) => {
                            if (result.isConfirmed) {
                                Swal.fire('Actualizado con exito', '', 'success')
                                this. BuscarUsuario();

                            } else if (result.isDenied) {
                                Swal.fire('Los cambios no se Actualizaron', '', 'warning')
                                return false
                            }
                        })
                    else {
                        alert("error")
                    }
                } catch (error) {
                    return "{\"sucess\":false ,\"message\":\"Error al intentar actualizar.\"}";
                }
            })
        },
    
        removeNotas(){
      // this.alumnos.splice(index,1);
      var endpoint = "http://localhost:8080/Agendas/eliminar/" + this.id;
            var opciones = { method: "DELETE" };
            fetch(endpoint, opciones).then(response => response.json())
              .then(data => { 
                this.usuario = data; console.log(data)
                Swal.fire({
              icon: "question",
              title: "Desea eliminar Nota",
              showDenyButton: true,
              showCancelButton: false,
              confirmButtonText: "Eliminar",
              denyButtonText: `Cancelar`,
            }).then((result) => {
              if (result.isConfirmed) {
                if (Response.status)
                Swal.fire("Elimado con exito", "", "success");
                this.BuscarUsuario();
                this.id=null,
                this.title="",
                this.fecha=null,
                this.contenido=""
                this.i=0
                // this.$router.push("/");
              } else if (result.isDenied) {
                Swal.fire("Los cambios no se guardaron", "", "warning");
                return false;
              }
            })
               
              })
               
    },






    },
    mounted: function(){
        this.BuscarUsuario();
    }
}
</script>