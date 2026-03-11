<template>
    <v-app>
        <v-app-bar>
            <v-app-bar-title>Tasks</v-app-bar-title>
            <v-spacer />
            <v-btn variant="text" to="/tasks">List</v-btn>
            <v-btn variant="text" to="/tasks/new">New</v-btn>
        </v-app-bar>

        <v-main>
            <router-view />
        </v-main>
    </v-app>
</template>