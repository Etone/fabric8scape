<template>
  <div class="home">
    <h1 class="text-h1 header">Admin</h1>
    <v-divider />

    <v-data-table :headers="headers" :items="items" class="elevation-1">
      <template v-slot:item.controls= "{ item }">
        <v-btn fab dark small color="grey" @click="deployPool(item.id)">
          <v-icon>fas fa-play</v-icon>
        </v-btn>
        <v-btn fab dark small color="grey" @click="undeployPool(item.id)">
          <v-icon>fas fa-pause</v-icon>
        </v-btn>
        <v-btn fab dark small color="grey" @click="deletePool(item.id)">
          <v-icon>fas fa-trash</v-icon>
        </v-btn>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      headers: [
        {
          text: "Id",
          value: "id",
        },
        {
          text: "Image",
          value: "image",
        },
        {
          text: "Creator",
          value: "creator",
        },
        {
          text: "",
          value: "controls",
          sortable: false,
        },
      ],
    };
  },
  asyncComputed: {
    async items() {
      // Request form Admin Backend Service
      // Format accordingly
      return [
        {
          image: "hello-world:latest",
          creator: "TEST-Test.v1",
          id: 12345,
        },
      ];
    },
  },
  methods: {
    deployPool: function(id) {
      console.log(id);
    },
    undeployPool: function(id) {
      console.log(id);
    },
    deletePool: function(id) {
      console.log(id);
    }
  }
};
</script>

<style scoped>
.header {
  text-align: center;
}
</style>
