
<template>
  <v-app id="inspire">
    <v-navigation-drawer app>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title"> Fabric8Scape </v-list-item-title>
          <v-list-item-subtitle> Showcase of fabric8 API </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          :to="item.route"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>


<script>
export default {
  data() {
    return {
      items: [
        {
          title: "Admin",
          icon: "fas fa-tools",
          route: "/",
        },
        {
          title: "Registry",
          icon: "fas fa-stream",
          route: "/registry",
        },
        {
          title: "Landscaper",
          icon: "fas fa-mountain",
          route: "/landscaper",
        },
      ],
      right: null,
    };
  },
};
</script>