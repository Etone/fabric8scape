<template>
  <div class="home">
    <h1 class="text-h1 header">Admin</h1>
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
    createPool: async function(pool) {
      await this.$adminService.createDataPool(pool);
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
}
</style>
