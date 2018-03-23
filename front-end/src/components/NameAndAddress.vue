<template>

  <div>


  <md-tabs>
    <md-tab id="bio" md-label="Name And Address">
      <customer v-model="customer" @saved="startQuote"></customer>
    </md-tab>

    <md-tab id="vehicles" md-label="Vehicles">
      <vehicle v-model="customer.vehicles"></vehicle>
    </md-tab>

    <md-tab id="drivers" md-label="Drivers">
    </md-tab>

    <md-tab id="final" md-label="Final Details" md-tooltip="This is the pictures tab!">
    </md-tab>
  </md-tabs>


    <md-snackbar md-position="top center" ref="snackbar" :md-duration="4000">
      <span>{{message}}</span>
      <md-button class="md-accent" md-theme="light-blue" @click.native="$refs.snackbar.close()">Ok</md-button>
    </md-snackbar>

  </div>

</template>

<script>

  export default {

    data:function(){

        return {
          customer: {},
          vechicles: [],
          page: "name-and-address",
          backend: "http://localhost:8080/insurance-service/",
          message: ""
        };
    },

    created: function(){
      var backend = new hybind(this.backend);

      var customer = {};
      backend.$bind("customer", customer);

      var vehicles = [];
      backend.$bind("vehicle", vehicles);

      this.customer = customer;
      this.vehicles = vehicles;
    },


    methods:{

      startQuote: function(){

          var me = this;

        this.customer.$create(this.customer).then(
          function(customer) {
            console.log(customer);

            me.customer = customer;

            me.message="Successfully Saved";
            me.$refs.snackbar.open();
          },
          function(err){
            console.log(err);

              me.error(err);
          }
        );
      },

      nextVehicles: function(){

          var me = this;

          this.vehicles.forEach(function (vehicle) {

//            vehicle.$load().customer.$set(me.customer);
            me.vehicles.$create(vehicle).customer.$set(me.customer);

          });
      },

      error: function(message){

          this.message = message.responseJSON.message;
          this.$refs.snackbar.open();
      }

    }

  }

</script>
