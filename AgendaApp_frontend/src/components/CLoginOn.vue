<template>
       <div class="margen">
        <div class="bg-img">
            <form action="#" class="container">
                <div class="tituloradio">
                    <h1 class="titulo">AGENDA ONLINE</h1>

                    <div class="columnas2">
                        <div class="img">
                            <img src="../../public/img/agregar.png" height="60px">
                            <router-link to="registronotas" class="botonlogeado">Agregar</router-link>
                           
                        </div><br>
                    
                        <div>
                            <img src="../../public/img/misnotas.png" height="60px">
                            <router-link to="misnotas" class="botonlogeado">Mis Notas</router-link>
                        </div><br>
                    <div>
                        <img src="../../public/img/contactos.png" height="60px">
                        <router-link to="/registrocontacto" class="botonlogeado">Contactos</router-link>
                    </div><br>
                    <div>
                        <img src="../../public/img/salir.png" height="60px">
                        <input class="botonlogeado" type="button" v-on:click="salirOFF" value="Salir">
                    </div><br>
                </div>
             </div>
         </form>
     </div>
</div>
                <footer>
    <p class="footer">Todos los derechos reservados. Esneider Villegas Duarte 
        <strong>Diciembre 2022</strong>
    </p>
</footer>
</template>

<script>
export default{
    data() {
        name:"CLoginOn"
        return {
            
        }
    },
    methods:{
        salirOFF(){
            Swal.fire({
              icon: "question",
              title: "Desea Salir",
              showDenyButton: true,
              showCancelButton: false,
              confirmButtonText: "Salir",
              denyButtonText: `Cancelar`,
            }).then((result) => {
              if (result.isConfirmed) {
                Swal.fire({
                    title:"Cerrando Sesión",
                    icon:"success",
                    timer:1000
                });
                 this.$router.push("/");
              }
            }
            )
    }
}
}
</script>