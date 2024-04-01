<template>
      <div class="margen">
        <div class="bg-img">
            <form action="#" class="container" method="POST" @submit.prevent="ValidarUsuario">
                <div class="tituloradio">
                    <h1 class="titulo">AGENDA ONLINE</h1>
                    <div class="img">
                        <img src="../../public/img/calendario.png" height="80px">
                    </div><br><br>
                    <label for="email"><b></b></label>
                    <input class="campos" type="email" placeholder="Correo Electronico" name="email" v-model="correo" required><br><br>

                    <label for="password"><b></b></label>
                    <input class="campos" type="password" placeholder="Contraseña" name="password" v-model="contrasena" required autocomplete="off"><br><br>

                   <div>
                    <router-link to="/login"></router-link>
                   </div>

                    <div>
                        <button class="boton" type="submit">INICIAR SESIÓN</button>
                    </div>
                    <br>

                    <div>
                        <router-link to="/recuperar">¿Has olvidado tu contraseña?</router-link>
                    <br><br>
                    </div>

                    <p>
                        <u class="nodeco">¿No tienes una cuenta? </u></p>

                    <div class="botones">
                       <router-link to="/registrar">¡REGISTRATE AQUI!</router-link> 
                    </div>


                </div>  
            </form>
        </div>
    </div>

</template>

<script>

export default{
    data() {
        name:"CHomeView"
        return {
            correo:'',
            contrasena:''
        }
    },
    methods:{
        ValidarUsuario() {
      var endpoint = "http://localhost:8080/usuarios/login/" + this.correo + '/' + this.contrasena;
      var opciones = { method: "GET" }
      fetch(endpoint, opciones).then(async response => {
        const resultado = await response.json();
        if (resultado.length >= 1) {
          Swal.fire({
            width: 300,
            icon: 'success',
            title: 'Correcto',
            text: "Usuario Registrado",
            showConfirmButton: false,
            timer: 500
          })
          this.$router.push('/login')
        }
        else if (!resultado.length) {
          Swal.fire({
            width: 300,
            icon: 'question',
            title: 'Error',
            text: "Usuario no encontrado",
            showConfirmButton: false,
            timer: 500
          })
        }
        else
          Swal.fire({
            width: 300,
            icon: 'error',
            title: 'Error',
            text: "Usuario o Clave Incorrectos",
            showConfirmButton: false,
            timer: 2000
          })
       
      })
    }
    }
}

</script>