<template>

  <md-card>

    <md-card-header>
      <div class="md-title">Provide Policyholder Name and Address</div>
      <div class="md-subhead">Subtitle here</div>
    </md-card-header>

    <md-card-actions>
      <md-button @click="startQuote">Ok. Start My Quote</md-button>
    </md-card-actions>

    <md-card-content>

      <form novalidate @submit.stop.prevent="submit">
        <md-input-container>
          <label>First name</label>
          <md-input v-model="value.firstName"></md-input>
        </md-input-container>

        <md-input-container>
          <label>Last name</label>
          <md-input v-model="value.lastName"></md-input>
        </md-input-container>

        <md-input-container>
          <label>Middle initial</label>
          <md-input></md-input>
        </md-input-container>

        <md-input-container md-inline>
          <label>Suffix</label>
           <md-select name="Suffix" v-model="value.suffix">
                <md-option value="mr">Mr.</md-option>
                <md-option value="miss">Miss</md-option>
              </md-select>
        </md-input-container>
      </form>

    </md-card-content>
  </md-card>
</template>

<script>

  export default {
    name: 'customer',

    props: {
      value: Object,
      option: Boolean

    },
    data(){




    },

    methods:{
      startQuote(){

        this.$emit('saved', this.value);

      }

    }

  }


</script>
