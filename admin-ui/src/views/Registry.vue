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
  computed: {
    items: function () {
      return [
        {
          id: 12345,
          creator: "Sys 12345",
        },
      ];
    },
  },
};
</script>

<style scoped>
.header {
  text-align: center;
}
</style>
