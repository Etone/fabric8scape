<template>
  <div class="home">
    <h1 class="text-h1 header">Admin</h1>
    <v-divider />
    <h2 class="text-h2 header">Create New Pool</h2>
    <v-forms @submit.prevent="this.createPool()">
      <v-row>
        <v-col cols="4">
          <v-text-field v-model="newPool.image.repository" label="Repository" />
        </v-col>
        <v-col cols="1">
          <v-text-field v-model="newPool.image.tag" label="Tag" />
        </v-col>
        <v-col cols="2">
          <v-text-field v-model="newPool.creator.system" label="System" />
        </v-col>
        <v-col cols="2">
          <v-text-field
            v-model="newPool.creator.environment"
            label="Environment"
          />
        </v-col>
        <v-col cols="2">
          <v-text-field v-model="newPool.creator.version" label="Version" />
        </v-col>
        <v-col cols="1"><v-btn color="green" fab @click="createPool()"><v-icon color="white">fas fa-plus-circle</v-icon></v-btn></v-col>
      </v-row>
    </v-forms>
    <v-divider />
    <v-data-table :headers="headers" :items="items" class="elevation-1">
      <template v-slot:item.controls="{ item }">
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
      newPool: {
        image: {},
        creator: {},
      },
    };
  },
  asyncComputed: {
    items: {
      async get() {
        const response = await this.$adminService.getDataPools();
        if (response.data) {
          return response.data.map((item) => {
            return {
              id: item.id,
              image: `${item.image.repository}:${item.image.tag}`,
              creator: `${item.creator.system.toUpperCase()}-
              ${item.creator.environment}.${item.creator.version}`,
            };
          });
        }
      },
      default() {
        return [];
      },
    },
  },
  methods: {
    deployPool: async function (id) {
      await this.$adminService.deployDataPool(id);
    },
    undeployPool: async function (id) {
      await this.$adminService.undeployDataPool(id);
    },
    deletePool: async function (id) {
      await this.$adminService.deleteDataPool(id);
    },
    createPool: async function () {
      console.log(this.newPool);
      await this.$adminService.createDataPool(this.newPool);
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
}
</style>
