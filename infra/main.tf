terraform {
  required_providers {
    cloudflare = {
      source  = "cloudflare/cloudflare"
      version = "~> 5"
    }

    neon = {
      source  = "kislerdm/neon"
      version = "0.13.0"
    }
  }
}

provider "neon" {
  api_key = var.neon_api_key
}

provider "cloudflare" {
  api_token = var.cloudflare_api_token
}

resource "neon_project" "project" {
  org_id                    = var.neon_org_id
  history_retention_seconds = 21600

  branch {
    name = "production"
  }
}

resource "neon_role" "role" {
  branch_id  = neon_project.project.branch[0].id
  name       = "application_user"
  project_id = neon_project.project.id
}

resource "neon_database" "postgres" {
  branch_id  = neon_project.project.branch[0].id
  name       = "project-db"
  project_id = neon_project.project.id
  owner_name = neon_role.role.name
}

resource "cloudflare_r2_bucket" "project-image-bucket" {
  account_id = var.cloudflare_account_id
  name       = "project-image"
}

resource "cloudflare_r2_bucket" "user-avatar-bucket" {
  account_id = var.cloudflare_account_id
  name       = "user-avatar"
}