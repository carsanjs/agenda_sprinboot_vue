<template>
     <div class="bg-img">
        <form action="#" class="container">

                <h1 class="titulo">Digita tu correo electrónico</h1><br><br>
                <div class="img">
                    <img src="../../public/img/contraseña.png" height="80px">
                </div><br><br><br>
                

                <label for="psw"><b></b></label>
                <input class="campos" type="email" placeholder="Correo electrónico" name="email"><br><br><br>

                <div class="botones">
                    
                    <a class="boton" href="../../public/img/index.html">RECUPERAR CONTRASEÑA</a>
                </div>
                
        </form>
    </div>
</template>

<script>
export default{

    data() {
        name:"CRecuperar"
        return {
            

        }
    },
    components:{

    }
}

</script>