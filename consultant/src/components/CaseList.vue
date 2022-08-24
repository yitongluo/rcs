<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by candidate name"
          v-model="candidateName"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchCandidateName"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Cases List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(_case, index) in cases"
          :key="index"
          @click="setActiveCase(_case, index)"
        >
          {{ _case.candidateName }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllCases">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentCase">
        <h4>Case</h4>
        <div>
          <label><strong>candidateName:</strong></label> {{ currentCase.candidateName }}
        </div>
        <div>
          <label><strong>accessCode:</strong></label> {{ currentCase.accessCode }}
        </div>
        <div>
          <label><strong>Status:</strong></label> {{ currentCase.published ? "Published" : "Pending" }}
        </div>

        <router-link :to="'/cases/' + currentCase.id" class="badge badge-warning">Edit</router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Case...</p>
      </div>
    </div>
  </div>
</template>

<script>
import CaseApi from "../api/CaseApi";
export default {
  name: "cases-list",
  data() {
    return {
      cases: [],
      currentCase: null,
      currentIndex: -1,
      candidateName: ""
    };
  },
  methods: {
    retrieveCases() {
      CaseApi.getAll()
        .then(response => {
          this.cases = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveCases();
      this.currentCase = null;
      this.currentIndex = -1;
    },
    setActiveCase(inputcase, index) {
      this.currentCase = inputcase;
      this.currentIndex = inputcase ? index : -1;
    },
    removeAllCases() {
      CaseApi.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchCandidateName () {
      CaseApi.findByName(this.candidateName)
        .then(response => {
          this.cases = response.data;
          this.setActiveCase(null);
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveCases();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>