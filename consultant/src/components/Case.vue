<template>
  <div v-if="currentCase" class="edit-form">
    <h4>ALL CASE</h4>
    <form>
      <div class="form-group">
        <label for="candidateName">candidateName</label>
        <input type="text" class="form-control" id="candidateName"
          v-model="currentCase.candidateName"
        />
      </div>

      <div class="form-group">
        <label for="candidateName">accessCode</label>
        <input type="text" class="form-control" id="accessCode"
          v-model="currentCase.accessCode"
        />
      </div>
    
      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentCase.published ? "Published" : "Pending" }}
      </div>
    </form>

    <button class="badge badge-primary mr-2"
      v-if="currentCase.published"
      @click="updatePublished(false)"
    >
      UnPublish
    </button>
    <button v-else class="badge badge-primary mr-2"
      @click="updatePublished(true)"
    >
      Publish
    </button>

    <button class="badge badge-danger mr-2"
      @click="deleteCase"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
      @click="updateCase"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Case...</p>
  </div>
</template>

<script>
import CaseApi from "../api/CaseApi";
export default {
  name: "Case",
  data() {
    return {
      currentCase: null,
      message: ''
    };
  },
  methods: {
    getCase(id) {
      CaseApi.get(id)
        .then(response => {
          this.currentCase = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updatePublished(status) {
      var data = {
        id: this.currentCase.id,
        candidateName: this.currentCase.candidateName,
        accessCode: this.accessCode,
        status: status, 
      };
      CaseApi.update(this.currentCase.id, data)
        .then(response => {
          console.log(response.data);
          this.currentCase.status = status;
          this.message = 'The status was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateCase() {
      CaseApi.update(this.currentCase.id, this.currentCase)
        .then(response => {
          console.log(response.data);
          this.message = 'The Case was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteCase() {
      CaseApi.delete(this.currentCase.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "cases" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getCase(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>