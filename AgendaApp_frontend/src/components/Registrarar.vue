<template>
  <div class="margen">
    <div class="bg-img">
      <form action="#" class="container" @submit.prevent="registrousuario" method="POST">
        <div class="tituloradio">
          <h1 class="titulo">REGISTRO</h1>
          <div class="img">
            <img src="../../public/img/registrarse.png" alt="Registro">
          </div>
          <br>
          <br>
          <label for="namecomplete"><b></b></label>
          <input class="campos" type="text" placeholder="Cedula" name="namecomplete" v-model="id" required><br><br>

          <label for="namecomplete"><b></b></label>
          <input class="campos" type="text" placeholder="Nombre completo" name="namecomplete" v-model="nombre"
            required><br><br>

          <label for="email"><b></b></label>
          <input class="campos" type="email" placeholder="Correo electrónico" name="psw" v-model="correo"
            required><br><br>

          <label for="password"><b></b></label>
          <input class="campos" type="password" placeholder="Contraseña" name="psw" v-model="contrasena"
            required><br><br>

          <label for="password2"><b></b></label>
          <input class="campos" type="password" placeholder="Confirmar contraseña" name="contrasena_2"
            v-model="contrasena_2" required><br><br>
          <div class="botones">
            <button type="submit" class="boton">REGISTRAR CUENTA</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      id: "",
      nombre: "",
      correo: "",
      contrasena: "",
      contrasena_2: ""
    }
  },
  components: {

  },
  methods: {
    registrousuario() {
      if (this.id != "" && this.nombre != "" && this.correo != "" && this.contrasena != ""
        && this.contrasena_2 != "") {
        var endpoint = "http://localhost:8080/usuarios/guardar";
        var opciones = {
          method: "POST",
          headers: { "Content-type": "Application/json" },
          body: JSON.stringify({
            id: this.id,
            nombre: this.nombre,
            correo: this.correo,
            contrasena: this.contrasena,
            contrasena_2: this.contrasena_2,

          }),
        };
        fetch(endpoint, opciones).then(async (Response) => {
          try {
            if (Response.status)
              Swal.fire({
                icon: "question",
                title: "Desea Registrarse?",
                showDenyButton: true,
                showCancelButton: false,
                confirmButtonText: "Registrar",
                denyButtonText: `Cancelar`,
              }).then((result) => {
                if (result.isConfirmed) {
                  Swal.fire("Guardado con exito", "", "success");
                  this.$router.push("/");
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
  }
}

</script>