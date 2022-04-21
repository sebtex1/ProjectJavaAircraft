<template>
  <div>
    <br>
    <h1>Aircraft Stats</h1>
 
    <gmap-map
      :center="center"
      :zoom="5"
      style="width:100%;  height: 48rem;"
    >
      <gmap-marker
        :key="index"
        v-for="(m, index) in markers"
        :position="m"
        :icon="aircraftIcon"
      ></gmap-marker>
    </gmap-map>
 
  </div>
</template>
 
<script>
import Aircraft from "../assets/Aircraft.png"

export default {
  name: "GoogleMap",
  data() {
    return {
      center: { lat: 48, lng: 8 },
      markers: [],
      currentPlace: null,
      aircraftIcon: Aircraft
    };
  },
 
  mounted() {
    this.geolocate();
  },
 
  methods: {
    setPlace(place) {
      this.currentPlace = place;
    },
    geolocate: function() {
 
        navigator.geolocation.getCurrentPosition(position => {
          this.center = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
        });
 
        this.markers = [
          {
              lat: 47.4559,
              lng: 8.5438,
              label: 'SWR195U'
          },
          {
              lat: 43.7387,
              lng: 0.2526,
              label: 'SWR76AT'
          },
          {
              lat: -0.9138,
              lng: 51.9242,
              label: 'GTICH'
          }
      ];
 
    }
  }
};
</script>

<style scoped>
</style>