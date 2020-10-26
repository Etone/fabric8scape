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
    async items() {
      const response = await this.$registryService.getDeployedPools().then((response) => response.data);
      return response.map((item) => {
        return {
          id: item.id,
          creator: `${item.creator.system.toUpperCase()}-${item.creator.environment}.${item.creator.version}`,
        }
      });
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
}
</style>
