<template>

  <div>

    <md-card v-if="page=='name-and-address'">
      <md-card-header>
        <div class="md-title">Name And Address</div>
        <div class="md-subhead">Subtitle here</div>
      </md-card-header>

      <md-card-actions>
        <md-button @click.native="next();">Next</md-button>
        <md-button>Cancel</md-button>
      </md-card-actions>

      <md-card-content>
        <customer v-model="customer"></customer>
      </md-card-content>
    </md-card>

    <md-card v-if="page=='vehicles'">

      <md-card-header>
        <div class="md-title">Vehicles</div>
        <div class="md-subhead">Subtitle here</div>
      </md-card-header>

      <md-card-actions>
        <md-button @click.native="nextVehicles();">Next</md-button>
        <md-button>Previous</md-button>
      </md-card-actions>

      <md-card-content>

        <object-grid ref="vehicles"
                     java="com.autoinsurance.Vehicle"
                     :serviceLocator="backend"
                     :data = "vehicles"
                     :full-fledged="true"
        >
        </object-grid>
      </md-card-content>
    </md-card>

    <md-snackbar md-position="bottom center" ref="snackbar" :md-duration="4000">
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

      next: function(){

          var me = this;

        this.customer.$create(this.customer).then(
          function(customer) {
            console.log(customer);

            me.customer = customer;

            me.page = "vehicles";
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
