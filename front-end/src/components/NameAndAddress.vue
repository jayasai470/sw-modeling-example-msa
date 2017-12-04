<template>

  <div>
    <object-form ref="object-form"
                 java="com.autoinsurance.Customer"
                 :data = "{}"
                 serviceLocator="http://localhost:8080/insurance-service"
    >
    </object-form>

    <md-button class="md-primary" @click.native="next();">다음</md-button>
  </div>

</template>

<script>

  export default {

    methods:{

        next: function(){

          this.$refs['object-form'].submit_();

        }

    }

  }

</script>
