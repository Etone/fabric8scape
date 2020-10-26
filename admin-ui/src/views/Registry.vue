<template>
  <div class="home">
    <h1 class="text-h1 header">Registry</h1>
    <v-divider />
    <v-data-table :headers="headers" :items="items" class="elevation-1" />
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
          text: "Creator",
          value: "creator",
        },
      ],
    };
  },
  asyncComputed: {
    items: {
      async get() {
        const response = await this.$registryService.getDeployedPools();
        if (response.data) {
          return response.data.map((item) => {
            return {
              id: item.id,
              creator: `${item.creator.system.toUpperCase()}-${
                item.creator.environment
              }.${item.creator.version}`,
            };
          });
        }
      },
      default() {
        return [];
      },
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
}
</style>
