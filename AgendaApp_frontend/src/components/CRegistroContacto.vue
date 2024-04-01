<template>
    <div id="root" class="container">
    <div class="margen">
        <div class="bg-img">
            <form @submit.prevent="RegistrarContacto" class="card p-5 formcenter" >
                <h1 class="titulo">REGISTRAR CONTACTO</h1>
                    <div class="img">
                        <img src="../../public/img/registrarse.png" height="80px">
                    </div>
    <div class="class-content-from">
      <div class="classflex classinput">
        <div>
          <div v-if="(i===-1)">
          <div class="form-group">
            <input class="id form-control" type="text" placeholder="id" name="id" v-model="id" required>
        </div>    
        </div>
        <div v-else>
          <div class="form-group">
         <input class="id" type="text" placeholder="id" name="id" v-model="id" disabled>
        </div>   
        </div>
        </div> 
        <div class="form-group">
        <input type="text" class="form-control" placeholder="Nombre" v-model="nombre" required>
       </div>
       <div class="form-group">
        <input type="email" class="form-control" placeholder="correo" v-model="correo" required>
        </div> 
        <div class="form-group">
        <input type="text" class="form-control" placeholder="telefono" v-model="telefono" required>
        </div> 
     </div>
    <div>
    <div v-if="i===-1"> 
      <button type="submit" class="btn btn-success"><span>Registrar</span> </button> 
    </div>

    <div v-else>
      <button type="submit" class="btn btn-success" v-on:click="Actualizar"><span>Actualizar</span> </button> 
      <button type="submit" class="btn btn-success" v-on:click="remove"><span>Eliminar</span></button>
    </div>
  </div>
  </div>
           </form>
        </div>
    </div>

    <div class="margen">
        <div class="bg-img">
                <div class="tituloradio">
                <div class="divcontsearch divsearpad">
                   <input type="text" class="form-control my-3" placeholder="BuscarContacto" v-model="buscar">
                </div>
                <table class="table table-stipped"> 
                        <thead>
                          <tr>
                            <th scope="col">Cedula</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Telefono</th>
                            <th scope="col">Correo</th>
                            <th scope="col">Acciones</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="(item,index) in contactos">
                            <th scope="row">{{item.id}}</th>
                            <td scope="col">{{item.nombre}}</td>
                            <td scope="col">{{item.telefono}}</td>
                            <td scope="col">{{item.correo}}</td>
                            <td><button class="btn btn-success" @click="update(index)">Editar </button></td>
                          </tr>
                        </tbody>
                </table>
                </div>
        </div>
    </div>
</div>
</template>

<script>
export default{
    data() {
        return {
            buscar:'',
            id:'',
            nombre:'',
            correo:'',
            telefono:'',
            contactos:[],
            i:-1
        }
    },
    computed:{
    lista(){
      return this.contactos.filter(contacto=>contacto.nombre.toUpperCase().includes(this.buscar.toUpperCase()))
    }
  },
  methods:{
    RegistrarContacto() {
      if(this.id!=='' && this.nombre!=='' && this.correo!=='' && this.telefono!==''){
        if(this.i===-1){

      var endpoint = "http://localhost:8080/Contactos/guardar";
      var opciones = {
        method: "POST",
        headers: { "Content-type": "Application/json" },
        body: JSON.stringify({
          id: this.id,
          nombre: this.nombre,
          correo: this.correo,
          telefono: this.telefono,
        }),
      };
      fetch(endpoint, opciones).then(async (Response) => {
        try {    
            Swal.fire({
              icon: "question",
              title: "Desea Registrar Contacto?",
              showDenyButton: true,
              showCancelButton: false,
              confirmButtonText: "Registrar",
              denyButtonText: `Cancelar`,
            }).then((result) => {
              if (result.isConfirmed) {
                if (Response.status)
                Swal.fire("Guardado con exito", "", "success");
              } else if (result.isDenied) {
                Swal.fire("Los cambios no se guardaron", "", "warning");
                return false;
              }
            });
          } catch (error) {
          return '{"sucess":false ,"message":"Error al intentar registrar."}';
        }
      });
        }
        else{
           this.contactos[this.i].id= this.id;
           this.contactos[this.i].nombre= this.nombre;
           this.contactos[this.i].correo= this.correo;
           this.contactos[this.i].telefono= this.telefono;
            this.i = -1;
    }
    this.contactos.push({
          id: this.id,
          nombre: this.nombre,
          correo: this.correo,
          telefono: this.telefono,
})

       this.id= null,
       this.nombre='',
       this.correo = '',
       this.telefono = ''
      }
    },
    remove(){
      var endpoint = "http://localhost:8080/Contactos/eliminar/" + this.id;
            var opciones = { method: "DELETE" };
            fetch(endpoint, opciones).then(response => response.json())
              .then(data => { 
                this.contactos = data; console.log(data)
                Swal.fire({
              icon: "question",
              title: "Desea eliminar contacto",
              showDenyButton: true,
              showCancelButton: false,
              confirmButtonText: "Registrar",
              denyButtonText: `Cancelar`,
            }).then((result) => {
              if (result.isConfirmed) {
                if (Response.status)
                Swal.fire("Elimado con exito", "", "success");
                this.BuscarContacto();
                
              } else if (result.isDenied) {
                Swal.fire("Los cambios no se guardaron", "", "warning");
                return false;
              }
            })
               
              })
               
    },
    update(index){
      this.id = this.contactos[index].id;
      this.nombre = this.contactos[index].nombre;
      this.correo = this.contactos[index].correo;
      this.telefono = this.contactos[index].telefono;;
      this.i = index; 
    },  
    Actualizar() {
            var endpoint = "http://localhost:8080/Contactos/guardar";
            var opciones = {
                method: "POST",
                headers: { "Content-type": "Application/json" },
                body: JSON.stringify({ 
                    id:this.id,
          nombre: this.nombre,
          correo: this.correo,
          telefono: this.telefono
             })
            }
            fetch(endpoint, opciones).then(async Response => {
                try {
                    if (Response.status)
                        Swal.fire({
                            icon: 'question',
                            title: 'Desea Actualizar Contacto?',
                            showDenyButton: true,
                            showCancelButton: false,
                            confirmButtonText: 'Actualizar',
                            denyButtonText: `Cancelar`,
                        }).then((result) => {
                            if (result.isConfirmed) {
                                Swal.fire('Actualizado con exito', '', 'success')
                                this.BuscarContacto();

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
    BuscarContacto() {
            var endpoint = "http://localhost:8080/Contactos/vercontactos";
            var opciones = { method: "GET" };
            fetch(endpoint, opciones)
                .then(response => response.json())
                .then(data => { this.contactos = data; console.log(data) })
        }
  },
  mounted:function(){
    this.BuscarContacto();
  },
}

</script>