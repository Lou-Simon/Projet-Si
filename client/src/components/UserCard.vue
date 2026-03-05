<template>
  <div class="card h-100 shadow-sm user-card">
    <div class="card-body d-flex flex-column gap-2">
      <div class="d-flex align-items-center gap-3">
        <div class="user-avatar">
          {{ initials }}
        </div>
        <div>
          <h5 class="card-title mb-0">{{ user.pseudo }}</h5>
          <small class="text-muted">{{ user.email }}</small>
        </div>
      </div>
      <div v-if="user.role" class="mt-1">
        <span class="badge" :class="roleBadgeClass">{{ user.role }}</span>
      </div>
      <div class="mt-auto d-flex gap-2">
        <router-link
          :to="`/users/${user.pseudo}`"
          class="btn btn-sm btn-outline-primary"
        >
          Voir le profil
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserCard',
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  computed: {
    initials() {
      const pseudo = this.user?.pseudo ?? '?'
      return pseudo.slice(0, 2).toUpperCase()
    },
    roleBadgeClass() {
      const role = this.user?.role?.toLowerCase()
      if (role === 'admin') return 'bg-danger'
      if (role === 'moderator') return 'bg-warning text-dark'
      return 'bg-secondary'
    }
  }
}
</script>

<style scoped>
.user-card {
  border-radius: 12px;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}
.user-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.12) !important;
}
.user-avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: linear-gradient(135deg, #667eea, #764ba2);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 1rem;
  flex-shrink: 0;
}
</style>
